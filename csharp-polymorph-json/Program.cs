using csharp_polymorph_json;

using System.Text.Json.Serialization;
using System.Text.Json;
using System.Text.Encodings.Web;

var jsonSerializerOptions = new JsonSerializerOptions
{
    Encoder = JavaScriptEncoder.UnsafeRelaxedJsonEscaping,
    NumberHandling = JsonNumberHandling.AllowReadingFromString
};

if (args.Length > 0)
{
    while(true)
    {
        var l = Console.ReadLine();
        if(l == null)
            break;
        Handle(l);
    }
    return;
}

var e = new ExtendedMessage("some additional +- data");
var json = Serialize(e);
Handle(json);
var anotherJson = "{\"i\":\"42\",\"type\":\"AnotherMessage\",\"instant\":\"2023-09-13T19:23:24.7902487+2:00\"}";
Handle(anotherJson); 
var stillAnotherJsonWithMissingFields = "{\"type\":\"AnotherMessage\",\"instant\":\"2023-09-13T19:23:24.7902487+2:00\"}";
Handle(stillAnotherJsonWithMissingFields);


void Handle(string json)
{
    var o = Deserialize(json);
    var s = Serialize(o);
    Console.WriteLine(s);
}

object? Deserialize(string json)
{
    var b = JsonSerializer.Deserialize<BaseMessage>(json);
    var t = b.TypeFromTypeString();
    var o = JsonSerializer.Deserialize( json, t, jsonSerializerOptions );
    return o;
}

string Serialize<T>(T e)
{
    return JsonSerializer.Serialize(e, jsonSerializerOptions);
}