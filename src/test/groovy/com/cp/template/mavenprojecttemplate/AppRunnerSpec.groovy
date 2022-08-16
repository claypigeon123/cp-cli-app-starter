package com.cp.template.mavenprojecttemplate

import spock.lang.Specification

class AppRunnerSpec extends Specification {

    void "sanity test"() {
        expect:
        (4 + 2) == 6
    }
}
