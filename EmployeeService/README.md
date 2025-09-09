

Interesting Fact! (@RequestParam vs @ParamVariable)

Spring: @PathVariable vs @RequestParam

1. @RequestParam

   Extracts key-value pairs from the query parameters of the URL.

   RequestParam are used to access the values from query params of the URL

   Can be made optional using required = false.
   Multiple @RequestParams can be used in a single request (e.g., pagination, filtering, sorting).

   /products?page=2&size=20

   Example Code:
   @GetMapping("/products")
   public List<Product> getProducts(
   @RequestParam(required = false, defaultValue = "0") int page,
   @RequestParam(required = false, defaultValue = "10") int size) {
   // logic here
   }

4. @PathVariable

   Extracts values directly from the URI path.

   Typically used for identifiers that uniquely locate a resource.

   Values are mandatory by default.

   Example URL:

   /products/42

   Example Code:

   @GetMapping("/products/{id}")
   public Product getProduct(@PathVariable("id") Long productId) {
   // logic here
   }

   Spring Boot allows both @RequestParam and @PathVariable for most use cases
   
   Certain rules and norms are there to keep our API clean, consistent and maintainable
   
   PathVariable are used to access the values directly from the path variable of the URL
   
   @PathVariable are used for identifiers that uniquely locate a resource

5. Key Differences
   | Feature     | `@PathVariable`                   | `@RequestParam`                       |
   | ----------- | --------------------------------- | ------------------------------------- |
   | Source      | Path segment of the URI           | Query string parameters               |
   | Usage       | Identifiers, resource locators    | Filters, sorting, pagination, options |
   | Optional?   | No (by default, must be provided) | Yes (can set `required = false`)      |
   | Example URL | `/products/42`                    | `/products?page=2&size=20`            |

6. Best Practices

   Use @PathVariable for required values that define the resource (e.g., IDs).

   Use @RequestParam for optional values such as filters, pagination, or search criteria.

   Keeping a clean separation improves API readability and maintainability.


