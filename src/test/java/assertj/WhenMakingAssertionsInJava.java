package assertj;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class WhenMakingAssertionsInJava {

    @Test
    public void traditionalAssertions(){
        int age = 21;
        List<Integer> ages = Arrays.asList(10,20,21,30);
        Assert.assertEquals(age, 21);
        Assert.assertTrue("List of ages contain 40", ages.contains(age));
    }

    @Test
    public void assertJAssertions(){
        int age = 21;
        List<Integer> ages = Arrays.asList(10,20,21,30);
        Assertions.assertThat(age).isGreaterThanOrEqualTo(21);
        Assertions.assertThat(ages).contains(age);
        Assertions.assertThat(ages).contains(10).hasSize(4).allMatch(a -> a>=0 && a <=100);

    }
}
