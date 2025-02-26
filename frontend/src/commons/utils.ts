
export class Utils {

    public static toBase64(text:string):string{        
        return btoa(text);
    }

    public static toShowError(reference:any, message:string){
        reference['errorMessage'] = message        
        setTimeout(()=> reference['errorMessage']='', 5000)           
    }

    public static toShowSuccess(reference:any, message:string){
        reference['successMessage'] = message  
        setTimeout(()=> reference['successMessage']='', 5000)           
    }
}