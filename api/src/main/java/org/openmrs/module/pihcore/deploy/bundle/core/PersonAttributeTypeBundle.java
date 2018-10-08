package org.openmrs.module.pihcore.deploy.bundle.core;

import org.openmrs.PersonAttributeType;
import org.openmrs.module.haiticore.metadata.HaitiPersonAttributeTypes;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.pihcore.metadata.core.PersonAttributeTypes;
import org.springframework.stereotype.Component;

@Component
public class PersonAttributeTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing PersonAttributeTypes");
        install(PersonAttributeTypes.PROVIDER_IDENTIFIER);
        install(PersonAttributeTypes.FOUND_THROUGH_ACTIVE_CASEFINDING);
        install(PersonAttributeTypes.IS_IMMIGRANT);
        install(PersonAttributeTypes.IS_INDIGENOUS);
        this.install(HaitiPersonAttributeTypes.TELEPHONE_NUMBER);
        this.install(HaitiPersonAttributeTypes.TEST_PATIENT);
        this.install(HaitiPersonAttributeTypes.UNKNOWN_PATIENT);
        this.install(HaitiPersonAttributeTypes.MOTHERS_FIRST_NAME);

        log.info("Retiring old person attribute types");
        // the mother's name attribute was incorrectly added with a leading space in the uuid, we should remove this
        uninstall(possible(PersonAttributeType.class, " 8d871d18-c2cc-11de-8d13-0010c6dffd0f"), "invalid uuid");
        uninstall(possible(PersonAttributeType.class, PersonAttributeTypes.BIRTHPLACE.uuid()), "now using obs to record birthplace");
        uninstall(possible(PersonAttributeType.class, PersonAttributeTypes.BIOMETRIC_REFERENCE_NUMBER.uuid()), "never used, decided to store as identifier instead");
    }

}
