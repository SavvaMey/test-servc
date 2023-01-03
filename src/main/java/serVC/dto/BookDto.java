package serVC.dto;

import lombok.Getter;
import lombok.Setter;
import serVC.utils.validations.New;
import serVC.utils.validations.Update;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
public class BookDto {

    @NotNull(groups = Update.class)
    @Null(groups = New.class)
    private Integer id;

    @NotNull(groups = {Update.class, New.class})
    private String name;

    @NotNull(groups = {Update.class, New.class})
    private String isbn;

    @NotNull(groups = {Update.class, New.class})
    private Integer authorId;

    @NotNull(groups = {Update.class, New.class})
    private String releaseDate;
}
