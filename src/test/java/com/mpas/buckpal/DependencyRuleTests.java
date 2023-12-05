package com.mpas.buckpal;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.mpas.buckpal.archunit.HexagonalArchitecture;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

	@Test
	void validateRegistrationContextArchitecture() {
		HexagonalArchitecture.basePackage("com.mpas.buckpal")

				.withDomainLayer("application.domain")

				.withAdaptersLayer("adapter")
				.incoming("in.web")
				.outgoing("out.persistence")
				.and()

				.withApplicationLayer("application")
				.incomingPorts("port.in")
				.outgoingPorts("port.out")
				.and()

				.withConfiguration("configuration")
				.check(new ClassFileImporter()
						.importPackages("com.mpas.buckpal.."));
	}

	@Test
	void domainModelDoesNotDependOnOutside() {
		noClasses()
				.that()
				.resideInAPackage("com.mpas.buckpal.application.domain.model..")
				.should()
				.dependOnClassesThat()
				.resideOutsideOfPackages(
						"com.mpas.buckpal.application.domain.model..",
						"lombok..",
						"java.."
				)
				.check(new ClassFileImporter()
						.importPackages("com.mpas.buckpal.."));
	}

}
