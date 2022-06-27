package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
// @ScriptAssert : 오브젝트 오류 처리 (하지만 권장하지 않음) -> 면 제약이 많고 복잡하다. 그리고 실무에서는 검증 기능이 해당 객체의 범위를 넘어서는 경우들도 종종 등장하는데, 그런 경우 대응이 어렵다
// @ScriptAssert(lang="javascript", script = "_this.price * _this.quantity > 10000", message = "총합이 만원이 넘어야 함") 권장하지 않음
public class Item {

    // @NotNull(groups = UpdateCheck.class) // 수정 요구사항 추가
    private Long id;

    // @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    //@NotNull (groups = {SaveCheck.class, UpdateCheck.class})
    //@Range(min=1000, max=100000, groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    //@NotNull (groups = {SaveCheck.class, UpdateCheck.class})
    //@Max(value = 9999, groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
