package ro.uaic.info.AddressCorrector.utils;

import ro.uaic.info.AddressCorrector.database.Node;
import ro.uaic.info.AddressCorrector.models.NodeType;
import ro.uaic.info.AddressCorrector.models.NormalizedAddress;

public class CityNormalizer extends FieldNormalizer{
    public CityNormalizer(String addressField, NormalizedAddress normalizedAddress) {
        super(addressField, normalizedAddress);
    }
    @Override
    protected boolean isOnCorrectField(Node node) {
        return node.getType() == NodeType.CITY;
    }
}
