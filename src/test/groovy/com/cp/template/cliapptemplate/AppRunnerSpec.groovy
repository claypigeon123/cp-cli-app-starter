package com.cp.template.cliapptemplate

import spock.lang.Specification

class AppRunnerSpec extends Specification {

    void "sanity test"() {
        expect:
        (4 + 2) == 6
    }
}
