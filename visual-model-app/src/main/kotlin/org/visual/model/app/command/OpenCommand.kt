package org.visual.model.app.command

import org.visual.model.shared.KSlf4j
import org.visual.model.shared.KSlf4j.Companion.log
import picocli.CommandLine
import java.util.concurrent.Callable

@CommandLine.Command(name = "open", aliases = ["o"])
@KSlf4j
class OpenCommand:Callable<Int> {
    override fun call(): Int {
        log.info("open")
        return 0
    }
}