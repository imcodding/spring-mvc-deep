package spring.deep.itemservice.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import spring.deep.itemservice.domain.item.Item;

@Component
public class ItemValidator implements Validator{


    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //item == clazz
        //item == subItem 자식클래스
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        //errors => bindingResult 의 부모 클래스

        if(!StringUtils.hasText(item.getItemName())) {
            errors.rejectValue("itemName", "required"); // rejectValue 가 FieldError 생성해준다.
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }

        if(item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
    }
}
