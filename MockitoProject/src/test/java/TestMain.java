import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import java.util.Iterator;


public class TestMain {

    @Test
    public void iterator_will_return_hello_world(){
        //arrange
        Iterator i=mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //act
        String result=i.next()+" "+i.next();
        //assert
        assertEquals("Hello World", result);
    }
}
