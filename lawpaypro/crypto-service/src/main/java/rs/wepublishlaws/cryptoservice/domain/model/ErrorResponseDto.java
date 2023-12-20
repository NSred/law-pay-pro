package rs.wepublishlaws.cryptoservice.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {

    @JsonProperty("foreign_id")
    @SerializedName("foreign_id")
    private String foreignId;

    @JsonProperty("currency")
    @SerializedName("currency")
    private String currency;

    @JsonProperty("convert_to")
    @SerializedName("convert_to")
    private String convertTo;
}
