# Primitive Collections

This project contains Classes which are more or less analogous to
java.util.List, specialised for java primitives to avoid having to cast
them to wrapper objects.

## Building

All code is generated from templates. The java code itself should not be
modified. The templates and the script to generate the java code are
located in the `template` directory.

These script to generate the java source files is written in Ruby,
therefore a ruby interpreter needs to be installed and available in the
path. Basically, just run `ant jar` to run everything. The ant task to
run the script generation is not yet particularly robust, you may need
to update it, e.g. if you are running under Windows.


