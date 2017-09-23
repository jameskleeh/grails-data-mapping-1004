package myapp

import grails.gorm.DetachedCriteria
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
class DetachedCriteriaHasOneEqIntegrationSpec extends Specification {

	def "test a detachedCriteria eq query on a hasOne association"() {
		Parent.withTransaction {
			new Parent(name: "p", child: new Child(name: "c")).save()
		}

		when:
		Child.withTransaction {
			new DetachedCriteria(Child).build {
				eq "parent", null
			}.get()
		}

		then:
		notThrown Exception
	}

}
