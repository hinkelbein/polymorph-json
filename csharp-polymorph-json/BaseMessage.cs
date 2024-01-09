using System.Text.Json.Serialization;

namespace csharp_polymorph_json
{
    public class BaseMessage
    {
        [JsonPropertyOrder(-9)]
        public string type { get; set; }
        [JsonPropertyOrder(-8)]
        public string instant { get; set; }

        public BaseMessage()
        {
        }

        public BaseMessage(Type type)
        {
            this.type = type.Name;
            instant = DateTimeOffset.Now.ToString("O");
        }

        public Type TypeFromTypeString()
        {
            var ns = GetType().Namespace.ToString();
            return Type.GetType($"{ns}.{type}");
        }
    }
}
