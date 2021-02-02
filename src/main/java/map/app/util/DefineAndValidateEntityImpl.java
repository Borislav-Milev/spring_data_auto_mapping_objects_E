package map.app.util;

import map.app.domain.dto.GameAddDto;
import map.app.domain.dto.GameEditDto;
import map.app.domain.dto.UserLoginDto;
import map.app.domain.dto.UserRegisterDto;
import map.app.util.contract.DefineAndValidateEntity;
import map.app.util.contract.ValidationUtil;

import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static map.app.constant.Messages.*;
import static map.app.constant.Validations.NO_ENTITY;

public class DefineAndValidateEntityImpl implements DefineAndValidateEntity {

    public static final String PATTERN = "dd-MM-yyyy";

    private final ValidationUtil validationUtil;

    public DefineAndValidateEntityImpl(ValidationUtil validationUtil) {
        this.validationUtil = validationUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T execute(Class<T> entity, String... args) {
        T object;

        switch (entity.getSimpleName()) {
            case REGISTER_DTO -> object = (T) new UserRegisterDto(args[1], args[2], args[4]);

            case LOGIN_DTO -> object = (T) new UserLoginDto(args[1], args[2]);

            case ADD_GAME_DTO -> object = (T) new GameAddDto(args[1], Double.parseDouble(args[2]),
                    new BigDecimal(args[3]), args[4], args[5], args[6],
                    LocalDate.parse(args[7], DateTimeFormatter.ofPattern(PATTERN)));

            default -> {
                System.out.println(NO_ENTITY);
                return null;
            }
        }

        if (validateObject(entity)) return object;
        return null;
    }

    @Override
    public GameEditDto editGameValues(GameEditDto gameEditDto,String[] args) {
            for (int i = 2; i < args.length; i++) {
                gameEditDto.setId(Integer.valueOf(args[1]));
                String[] elements = args[i].split("=");
                String valueType = elements[0].toLowerCase();
                String value = elements[1];
                switch (valueType) {
                    case "size" -> gameEditDto.setSize(Double.valueOf(value));
                    case "price" -> gameEditDto.setPrice(new BigDecimal(value));
                    case "trailer" -> gameEditDto.setTrailer(value);
                    case "image thumbnail" -> gameEditDto.setImageThumbnail(value);
                    case "description" -> gameEditDto.setDescription(value);
                    case "release date" -> gameEditDto.setReleaseDate
                            (LocalDate.parse(value, DateTimeFormatter.ofPattern(PATTERN)));
                }
            }
        if(this.validateObject(gameEditDto)) return gameEditDto;
        return null;
    }


    private <T> boolean validateObject(T object) {
        if (!this.validationUtil.isValid(object)) {
            this.validationUtil.getViolations(object)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return false;
        }
        return true;
    }
}
