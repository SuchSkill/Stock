import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Price {
    LocalDate date;
    float open;
    float high;
    float low;
    float close;

}
