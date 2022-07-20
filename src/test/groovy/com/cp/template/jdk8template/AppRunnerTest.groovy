package com.cp.template.jdk8template

import spock.lang.Specification

class AppRunnerTest extends Specification {

    void "sanity test"() {
        expect:
        (4 + 2) == 6
    }
}
