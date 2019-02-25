package com.tesliukdev.fairgame

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.InstrumentationRegistry
import com.tesliukdev.fairgame.gameconnector.GameConnector
import com.tesliukdev.fairgame.gateway.Gateway
import com.tesliukdev.fairgame.screens.rockpapscis.RPSViewModel
import com.tesliukdev.fairgame.screens.rockpapscis.model.Move
import com.tesliukdev.fairgame.screens.rockpapscis.model.RPSModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.mockito.MockitoAnnotations
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.IOException
import com.tesliukdev.fairgame.dagger.DaggerTestComponent
import com.tesliukdev.fairgame.dagger.TestModule
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsApi
import com.tesliukdev.fairgame.gateway.cloud.rps.RpsCloud
import org.junit.*
import javax.inject.Inject


class RPSTest {

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()
    private val server = MockWebServer()
    @Inject
    lateinit var rpsApi: RpsApi
    lateinit var gateway: Gateway
    @Inject
    lateinit var gameConnector : GameConnector
    private lateinit var rpsModel : RPSModel
    private lateinit var rpsViewModel : RPSViewModel

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)

        TestModule.TEST_URL = server.url("/").toString()

        val component = DaggerTestComponent.builder()
                .cloudModule(TestModule()).build()
        component.inject(this)

        try {
            server.start()
        } catch (e: Exception) {

        }

        gateway = RpsCloud(rpsApi)
        gameConnector.gateway = gateway
        rpsModel = RPSModel(gameConnector)
        rpsViewModel = RPSViewModel(rpsModel)
    }

    @Test
    fun round_success() {
        val move = Move.PAPER

        server.enqueue(MockResponse()
                .setResponseCode(200)
                .setBody(getJson("rps_200_ok_response.json")))

        rpsViewModel.onClickMoveButton(move)

        val request = server.takeRequest()
        assertEquals("/json-rpc/1/invoke", request.path)

        assertEquals("You won", rpsViewModel.gameResult.get())
    }

    @Test
    fun round_error() {
        val move = Move.PAPER

        server.enqueue(MockResponse()
                .setResponseCode(405)
                .setBody(getJson("rps_405_not_valid_response.json")))

        rpsViewModel.onClickMoveButton(move)

        assert(rpsViewModel.errorMessage.get() != null)
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()

        try {
            server.shutdown()
        } catch (e: IOException) {
            // Do nothing
        }
    }

    private fun getJson(fileName: String): String {
        val stream = InstrumentationRegistry.getContext().assets.open(fileName)
        return okio.Buffer().readFrom(stream).readUtf8().format()
    }

}