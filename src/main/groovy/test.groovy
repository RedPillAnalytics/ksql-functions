import groovy.json.*

def post = new URL("https://mocksvc.mulesoft.com/mocks/74eea8f3-d287-473b-b452-3461a042201b/license-plate-numbers/?number-of-lpns=1").openConnection();
def message = ''
post.setRequestMethod("POST")
post.setDoOutput(true)
post.setRequestProperty("Content-Type", "application/json")
post.getOutputStream().write(message.getBytes("UTF-8"));
def postRC = post.getResponseCode();
//println(postRC);
if(postRC.equals(200)) {
    def result = post.getInputStream().getText();
    def jsonSlurper = new JsonSlurper()
    def object = jsonSlurper.parseText(result);
    println(object.lpns[0]);
    return object.lpns[0];
}
else {
    return -1;
}