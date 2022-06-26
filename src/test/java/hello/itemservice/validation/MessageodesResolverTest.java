package hello.itemservice.validation;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;

import static org.assertj.core.api.Assertions.*;

public class MessageodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();

    @Test
    void messageCodesResolverObject() {
        String[] massageCodes = codesResolver.resolveMessageCodes("required", "item");

        for (String result : massageCodes) {
            System.out.println("result = " + result);
        }


        // containsOnly: 순서, 중복을 무시하는 대신 원소값과 갯수가 정확히 일치
        // containsExactly: 순서를 포함해서 정확히 일치
        // contains : 중복여부, 순서에 관계 없이 값만 일치하면 테스트가 성공합니다.
        assertThat(massageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] massageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);

        for (String result : massageCodes) {
            System.out.println("result = " + result);
        }

        assertThat(massageCodes).containsExactly("required.item.itemName", "required.itemName", "required.java.lang.String", "required");
    }

}
