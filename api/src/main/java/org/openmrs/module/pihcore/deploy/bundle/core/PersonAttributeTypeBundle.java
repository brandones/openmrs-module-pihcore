package org.openmrs.module.pihcore.deploy.bundle.core;

import org.openmrs.PersonAttributeType;
import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.openmrs.module.metadatadeploy.bundle.CoreConstructors;
import org.openmrs.module.metadatadeploy.descriptor.PersonAttributeTypeDescriptor;
import org.openmrs.module.pihcore.metadata.core.PersonAttributeTypes;
import org.springframework.stereotype.Component;

@Component
public class PersonAttributeTypeBundle extends AbstractMetadataBundle {

    @Override
    public void install() throws Exception {

        log.info("Installing PersonAttributeTypes");
        install(PersonAttributeTypes.TELEPHONE_NUMBER);
        install(PersonAttributeTypes.TEST_PATIENT);
        install(PersonAttributeTypes.PROVIDER_IDENTIFIER);
        install(PersonAttributeTypes.UNKNOWN_PATIENT);
        install(PersonAttributeTypes.MOTHERS_FIRST_NAME);
        //install(PersonAttributeTypes.BIRTHPLACE);

        log.info("Retiring old person attribute types");
        // the mother's name attribute was incorrectly added with a leading space in the uuid, we should remove this
        uninstall(possible(PersonAttributeType.class, " 8d871d18-c2cc-11de-8d13-0010c6dffd0f"), "invalid uuid");
        uninstall(possible(PersonAttributeType.class, PersonAttributeTypes.BIRTHPLACE.uuid()), "now using obs to record birthplace");
    }

    //***** BUNDLE INSTALLATION METHODS FOR DESCRIPTORS

    protected void install(PersonAttributeTypeDescriptor d) {
        install(CoreConstructors.personAttributeType(d.name(), d.description(), d.format(), d.foreignKey(), d.searchable(), d.sortWeight(), d.uuid()));
    }
}