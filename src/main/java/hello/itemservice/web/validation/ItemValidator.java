package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
public class ItemValidator implements Validator { // 스프링이 제공하는 검증 인터페이스

    @Override
    public boolean supports(Class<?> clazz) { // 해당 검증기를 지원하는지 여부
        // instanceof는 특정 Object가 어떤 클래스/인터페이스를 상속/구현했는지를 체크
        // isAssignableFrom()은 특정 Class가 어떤 클래스/인터페이스를 상속/구현했는지 체크
        return Item.class.isAssignableFrom(clazz);
        // item == class
        // item == subItem
    }

    // 검증 대상 객체와 BindingResult
    @Override
    public void validate(Object target, Errors errors) { // Errors = BindingResult의 부모
        Item item = (Item) target;

        if (!StringUtils.hasText(item.getItemName())) {
            // bindingResult.addError(new FieldError("item", "itemName", item.getItemName(), false, new String[] {"required.item.itemName"}, null, null));
            errors.rejectValue("itemName", "required");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            // bindingResult.addError(new FieldError("item", "price", item.getPrice(), false,  new String[] {"range.item.price"}, new Object[]{1000, 100000}, null));
            errors.rejectValue("price", "range", new Object[]{1000, 100000}, null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 10000) {
            // bindingResult.addError(new FieldError("item", "quantity", item.getQuantity(), false, new String[] {"max.item.quantity"}, new Object[]{9999}, null));
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                // bindingResult.addError(new ObjectError("item", new String[]{"totalPriceMin"}, new Object[]{10000, resultPrice},null));
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
