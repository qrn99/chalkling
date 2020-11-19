package teamchalkling.chalkling;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping("/test")
    public Test setId(@RequestParam(value = "id", defaultValue = "0") int id) {
        return new Test(id);
    }
}
