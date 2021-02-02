package map.app.config;
import map.app.util.DefineAndValidateEntityImpl;
import map.app.util.ReaderImpl;
import map.app.util.ValidationUtilImpl;
import map.app.util.contract.Reader;
import map.app.util.contract.DefineAndValidateEntity;
import map.app.util.contract.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class AppConfiguration {

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public Reader reader(){
        return new ReaderImpl(this.stringReader());
    }

    @Bean
    public BufferedReader stringReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public DefineAndValidateEntity validateAndDefineEntity(){
        return new DefineAndValidateEntityImpl(this.validationUtil());
    }
}
