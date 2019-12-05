#header()

package ${package}.commands;
#set($command = $helper.getByName($command_name, $robot))
#set($params = $command.getProperty("Parameters").getValue())
import edu.wpi.first.wpilibj.command.InstantCommand;
import ${package}.Robot;

/**
 *
 */
public class #class($command.name) extends InstantCommand {

#@autogenerated_code("variable_declarations", "    ")
#parse("${exporter_path}Command-variable-declarations.java")
#end

#@autogenerated_code("constructor", "    ")
#parse("${exporter_path}Command-constructor.java")
#end
#@autogenerated_code("variable_setting", "        ")
#parse("${exporter_path}Command-variable-setting.java")
#end
#@autogenerated_code("requires", "        ")
#parse("${exporter_path}Command-requires.java")
#end
    }

    // Called once when this command runs
    @Override
    protected void initialize() {
    }

}