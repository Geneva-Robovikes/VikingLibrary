#header()

package ${package}.commands;
#set($command = $helper.getByName($command_name, $robot))

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import ${package}.Robot;

/**
 *
 */
public class #class($command.name) extends ConditionalCommand {


#@autogenerated_code("constructor", "    ")
#parse("${exporter_path}ConditionalCommand-constructor.java")
#end

    }

    @Override
    protected boolean condition(){
        return false;//TODO: Auto Generated method stub
    }
}