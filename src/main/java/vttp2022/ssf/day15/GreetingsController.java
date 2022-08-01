package vttp2022.ssf.day15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path={"/", ""})
public class GreetingsController {
      
      @Autowired
      private RedisTemplate<String, Object> redisTemplate;

      @GetMapping
      public String getGreetings(Model model) {
            System.out.println(">>> template: "+redisTemplate);
            ValueOperations<String, Object> ops = redisTemplate.opsForValue();

            System.out.println("after-----");
            Object greetings = ops.get("greetings");

            System.out.println("after-----" + greetings);
            model.addAttribute("greetings", greetings);
            return "index";
      }

      @PostMapping
      public String postGreetings(@RequestBody MultiValueMap<String, String> form, Model model) {
            String greetings = form.getFirst("greetings");

            redisTemplate.opsForValue().set("greetings", greetings);
            System.out.println("Message has been updated to " + greetings);
            model.addAttribute("greetings", greetings);
            return "index";
      }
}
