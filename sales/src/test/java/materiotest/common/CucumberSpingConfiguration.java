package materiotest.common;

import io.cucumber.spring.CucumberContextConfiguration;
import materiotest.SalesApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { SalesApplication.class })
public class CucumberSpingConfiguration {}
