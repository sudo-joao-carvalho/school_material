package domain

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

class FactoryInitTest {

    private val originalOut = System.out
    private val originalIn = System.`in`
    private val outputStreamCaptor = ByteArrayOutputStream()


    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun cleanUp() {
        System.setOut(originalOut)
        System.setIn(originalIn)
    }

    @Test
    fun alineaA1Test() {
        // Act
        Factory

        // Assert
        assert(outputStreamCaptor.toString().contains("Factory has been initialized!"))
    }

}