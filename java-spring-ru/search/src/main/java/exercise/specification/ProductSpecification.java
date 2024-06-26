package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withTitle(params.getTitleCont())
                .and(withCategory(params.getCategoryId()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withRating(params.getRatingGt()));
    }

    private Specification<Product> withTitle(String titleCont) {
        return (root, query, criteriaBuilder) -> titleCont == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("title"), titleCont);
    }

    private Specification<Product> withCategory(Long categoryId) {
        return (root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, criteriaBuilder) -> priceLt == null ? criteriaBuilder.conjunction() : criteriaBuilder.lessThan(root.get("price"), priceLt);
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, criteriaBuilder) -> priceGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withRating(Double ratingGt) {
        return (root, query, criteriaBuilder) -> ratingGt == null ? criteriaBuilder.conjunction() : criteriaBuilder.greaterThan(root.get("rating"), ratingGt);
    }
}
// END
