package math

import org.junit.jupiter.api.Test
import ru.vertuos.engine.math.Vector2f

class Vector2fTest {

    @Test
    fun test_that_x_and_y_packed_correctly() {
        val x = (1..1000).random().toFloat()
        val y = (1..1000).random().toFloat()
        val vector2f = Vector2f(x, y)
        assert(x == vector2f.x && y == vector2f.y)
    }
}
