#header()

#ifndef OI_H
\#define OI_H

\#include "frc/WPILib.h"

class OI {
private:
#@autogenerated_code("declarations", "	")
#parse("${exporter_path}OI-declarations.h")
#end
public:
	OI();

#@autogenerated_code("prototypes", "	")
#parse("${exporter_path}OI-prototypes.h")
#end
};

#endif
