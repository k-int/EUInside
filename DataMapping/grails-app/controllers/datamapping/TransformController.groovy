package datamapping

import grails.converters.XML


class TransformController {


    def transformationService
    def batchStatus

    def index() { }

    def fetch(){
        //TODO
    }

    def list(){
        return ["LIDO","LDO","EDM"] as XML
    }

    def statistics(){
        //TODO
    }

    def status(){
       return batchStatus
    }

    def Transform(){

        def f = request.getFile('file')
                       if (request.getFile('file').getOriginalFilename()) {

                          println   request.getFile('file').getOriginalFilename()
                       }

        def records = []
        if(params.zip){

            batchStatus = "Started unzip..."
            println   batchStatus

            def zipFile = new java.util.zip.ZipFile(f)

            zipFile.entries().each {
               println zipFile.getInputStream(it).text
                records.add(it)
            }

//TODO add each record to records array
            batchStatus = "Finished unzip..."

            records.each{ record ->
                def file = File(record)
                batchStatus = "Transformation begun on " + file.getOriginalFilename()
                println   batchStatus
                transformationService.transform(
                        file,
                        params.sourceFormat,
                        params.targetFormat)
            }

            batchStatus = "Finished transforming."

        }
        else if (params.record){
            transformationService.transform(
                    params.record,
                    params.sourceFormat,
                    params.targetFormat)
        }

    }

    def upload(String message){

               def f = request.getFile('myFile')
                if (request.getFile('myFile').getOriginalFilename()) {

                    uploader(f, message, params.album)
                    return
                }
         }

}
