package analyser

import JavaFile
import TestDataFiles
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.hasElement
import com.natpryce.hamkrest.isEmpty
import org.junit.jupiter.api.Test

class AnalyserTest {

    private val javaFile = JavaFile("EmptyClass.java", TestDataFiles.contentOf("end-to-end/EmptyClass.java"))


    @Test
    fun `reports conformance when given`() {
        val conformance = Conformant()
        val analyser = Analyser(listOf(Constraint { conformance }))
        assertThat(analyser.analyse(javaFile), hasElement(conformance))
    }

    @Test
    fun `reports violations when given`() {
        val violation = Violation("MyClass", "Oh no!")
        val analyser = Analyser(listOf(Constraint { violation }))
        assertThat(analyser.analyse(javaFile), hasElement(violation))
    }

    @Test
    fun `doesn't create report entries if there are no constraints`() {
        val analyser = Analyser(emptyList())
        assertThat(analyser.analyse(javaFile), isEmpty)
    }
}