package br.com.dwallet.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

public class ArchitectureNameTest {

    private JavaClasses importedClasses;

    @BeforeEach
    public void setup() {
        importedClasses = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages("br.com.dwallet");
    }

    @Test
    void services_classes_should_reside_in_package_service_or_operation() {
        classes()
                .that().resideInAPackage("service")
                .or().resideInAPackage("operation")
                .should().haveSimpleNameEndingWith("Service")
                .check(importedClasses);
    }

    @Test
    void repository_classes_should_reside_in_package_repository() {
        classes()
                .that().resideInAPackage("repository")
                .should().haveSimpleNameEndingWith("Repository")
                .check(importedClasses);
    }

    @Test
    void dto_classes_should_reside_in_package_dto() {
        classes()
                .that().resideInAPackage("dto..")
                .should().haveSimpleNameEndingWith("DTO")
                .check(importedClasses);
    }

    @Test
    void validators_classes_should_reside_in_package_validators() {
        classes()
                .that().resideInAPackage("..validator..")
                .should().haveSimpleNameEndingWith("Validator")
                .check(importedClasses);
    }

    @Test
    void translator_classes_should_reside_in_package_translator() {
        classes()
                .that().resideInAPackage("..translator..")
                .should().haveSimpleNameEndingWith("Translator")
                .check(importedClasses);
    }

    @Test
    void api_classes_should_reside_in_package_api() {
        classes()
                .that().resideInAPackage("..api..")
                .should().haveSimpleNameEndingWith("API")
                .check(importedClasses);
    }
}
