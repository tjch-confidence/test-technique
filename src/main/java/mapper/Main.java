package mapper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

public class Main {

    public static void main(String[] args) {
        // An object Store has been defined with 3 attributes : id, bu, partners
        // id is the store identifier in String, bu is the business unit of type BusinessUnitEnum,
        // partners is the list of partners of the store
        // A store must have an id, and must belong to only one BU but may have 0, 1 or 2 partners.
        // Here is a list of stores to work with:
        List<Store> stores = getStores();

        // complete the method buildMapGroupedByBUAndPartner to group the stores by BU and Partner
        Map<BusinessUnitEnum, Map<PartnerEnum, List<String>>> result = buildMapGroupedByBUAndPartner(stores);

        Map<BusinessUnitEnum, Map<PartnerEnum, List<String>>> expected = Map.of(
                BusinessUnitEnum.BU_FR, Map.of(
                        PartnerEnum.PARTNER_A, List.of("001", "002"),
                        PartnerEnum.PARTNER_B, List.of("002"),
                        PartnerEnum.ALL, List.of("001", "002")
                ),
                BusinessUnitEnum.BU_IT, Map.of(
                        PartnerEnum.PARTNER_B, List.of("001"),
                        PartnerEnum.ALL, List.of("001")
                ),
                BusinessUnitEnum.BU_ES, Map.of(
                        PartnerEnum.PARTNER_B, List.of("001"),
                        PartnerEnum.ALL, List.of("001")
                )
        );

        System.out.println(compareTwoResultMaps(expected, result));
    }

    /**
     * This method is to create a map containing the id of stores which have been grouped firstly by BU and then by Partner.
     * A store must belong to only one BU and may have 0, 1 or 2 partners.
     * The output map should have a fake partner "ALL" for stocking the stores with at least one partner in a BU.
     * The logic of method can be represented in following example.
     *
     * Example:
     *      input stores:
     *      [
     *          {bu: BU_FR, id: "001", partners: [PARTNER_A]},
     *          {bu: BU_FR, id: "002", partners: [PARTNER_A, PARTNER_B]},
     *          {bu: BU_IT, id: "001", partners: [PARTNER_B]},
     *          {bu: BU_ES, id: "001", partners: [PARTNER_B]},
     *          {bu: BU_ES, id: "002", partners: []}
     *      ]
     *
     *      output map:
     *      {
     *          BU_FR -> {
     *              PARTNER_A -> ["001", "002"],
     *              PARTNER_B -> ["002"],
     *              ALL -> ["001", "002"]
     *         },
     *         BU_IT -> {
     *              PARTNER_B -> ["001"],
     *              ALL -> ["001"]
     *         },
     *         BU_ES -> {
     *              PARTNER_B -> ["001"],
     *              ALL -> ["001"]
     *         }
     *     }
     *
     * @param stores: store list as input
     * @return a map containing the id of stores grouped by BU and Partner
     */
    private static Map<BusinessUnitEnum, Map<PartnerEnum, List<String>>> buildMapGroupedByBUAndPartner(List<Store> stores) {
        // TODO: implement the method
        return emptyMap();
    }

    public enum BusinessUnitEnum {
        BU_FR,
        BU_IT,
        BU_ES
    }

    public enum PartnerEnum {
        PARTNER_A,
        PARTNER_B,
        ALL
    }

    public static class Store {
        private final String id;

        private final BusinessUnitEnum bu;

        private final List<PartnerEnum> partners;

        public Store(String id, BusinessUnitEnum bu, List<PartnerEnum> partners) {
            this.id = id;
            this.bu = bu;
            this.partners = partners;
        }

        public String getId() {
            return id;
        }

        public BusinessUnitEnum getBu() {
            return bu;
        }

        public List<PartnerEnum> getPartners() {
            return partners;
        }
    }

    public static List<Store> getStores() {
        return List.of(
                new Store("001", BusinessUnitEnum.BU_FR, List.of(PartnerEnum.PARTNER_A)),
                new Store("002", BusinessUnitEnum.BU_FR, List.of(PartnerEnum.PARTNER_A, PartnerEnum.PARTNER_B)),
                new Store("001", BusinessUnitEnum.BU_IT, List.of(PartnerEnum.PARTNER_B)),
                new Store("001", BusinessUnitEnum.BU_ES, List.of(PartnerEnum.PARTNER_B)),
                new Store("002", BusinessUnitEnum.BU_ES, emptyList())
        );
    }

    private static boolean compareTwoResultMaps(Map<BusinessUnitEnum, Map<PartnerEnum, List<String>>> expected,
                                                Map<BusinessUnitEnum, Map<PartnerEnum, List<String>>> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }
        return expected.entrySet().stream().allMatch(e -> {
            Map<PartnerEnum, List<String>> actualValue = actual.get(e.getKey());
            Map<PartnerEnum, List<String>> expectedValue = e.getValue();
            if (actualValue == null || actualValue.size() != expectedValue.size()) {
                return false;
            }
            return expectedValue.entrySet().stream().allMatch(ee -> {
                List<String> actualList = actualValue.get(ee.getKey());
                List<String> expectedList = ee.getValue();
                return Arrays.equals(actualList.toArray(), expectedList.toArray());
            });
        });
    }
}
