package org.visual.model.debugger;

import com.sun.tools.attach.VirtualMachine;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestAttach {

    @Test
    void testVms(){
        val vms = VirtualMachine.list();
        vms.forEach(vm->{
            log.info(vm.displayName());
            log.info(vm.id());
            log.info(vm.provider().name());
            log.info(vm.provider().type());
        });
    }
}
