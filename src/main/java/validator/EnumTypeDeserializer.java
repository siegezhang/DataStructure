package validator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class EnumTypeDeserializer extends JsonDeserializer<YesNoEnum> {

    @Override
    public YesNoEnum deserialize(
            JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final ObjectCodec objectCodec = jsonParser.getCodec();
        final JsonNode node = objectCodec.readTree(jsonParser);
        final int type = node.asInt();
        for (YesNoEnum enum1 : YesNoEnum.values()) {
            if (enum1.status == type) {
                return enum1;
            }
        }
        return null;
    }
}
