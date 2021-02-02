package map.app.util.contract;

import map.app.domain.dto.GameEditDto;

public interface DefineAndValidateEntity {

    <T> T execute(Class<T> entity, String... args);

    GameEditDto editGameValues(GameEditDto gameEditDto,String[] args);
}
