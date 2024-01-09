Polymorph json serde compatible in java and csharp

Here are two projects, one java and one csharp with very similar structure and exact? similar behaviour.
There is a runner, Programm.cs and Main.java, which will produce three json strings, line by line, on stdout.
Each of these strings is the result of deserialization of some hard coded json string, followed be serialization of the deserialized objects. This is already part of round trip.
When called with any argument, the runners will instead read json strings from stdin, and derrsialize/serialize these as a a simple processor.
You can prove that cshar-java (and reverse) round trip is working with doing something like this in a parent directory:
PS C:\dev> ./csharp-polymorph-json\bin\Debug\net6.0\csharp-polymorph-json.exe | java -classpath .\java-polymorph-json/target\java-polymorph-json-1.0-SNAPSHOT-jar-with-dependencies.jar org.example.Main x
Here, the csharp implementation is used as a producer (no args), stdout is piped to the java implementation used as a processor (one arg "x"). This should also work in any other combination.

The deserializers used in both projects are configured to be tolerant with respect to data not in the model, and missing data.

The analogeous data model in both projects contain a base entity: BaseMessage, and two inheritors: ExtendedMessage and AnotherMessage.
In the BaseMassege there are two string fields: type and instant. instant is just there for fun.
type is used as a type discriminator for deciding on the concrete class to be deserialized.
Deserialization is implemented as a two step process: First the BaseMassege is deserialized, the type (csharp Type, java Class) is created from type field and then the whole thing is deserialized again into the concrete type.
There is some laziness implemented regarding namespace handling (namespaces are stripped when serialzing type).

Please note, that the infrastructure that is needed to implement this POC is rather minimal, about ten lines of code in total, and that there is only minimal dependencies, that are likely resolved in any real world project, already. System.Text.Json and com.fasterxml.jackson.core, respectively.
There is also about no annotations, decorations, and the like on the model classes.
Of course, it's just a quick and dirty hacky POC, but given the results, the effort, simplicity, dependencies, maintanability and flexibility, I think this shows a liable way to solve the problem.
