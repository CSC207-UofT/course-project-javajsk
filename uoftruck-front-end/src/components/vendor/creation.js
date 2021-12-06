import axios from "axios";
import { useState, useContext, useEffect} from "react";
import { Redirect } from "react-router";
import { domain, port} from './../../mechanisms/contexts'
import {GetShopId, GetToken} from './../../mechanisms/authenticateTokens';

function SingletonCreation(data,arr, event, setResp,token, shopId){
    event.preventDefault();
    let finalDat = {};
    finalDat['name'] = data.name;
    finalDat['price'] = data.price;
    finalDat['description'] = data.description;
    finalDat['name'] = data.name;
    let store = {};
    if(data.acceptedTypes == null){
        setResp({status:403,message:"Please fill in all values.", contents: "'"});
        return;
    }
    
    
    for (var i = 0; i < arr.length; i++) {
        if(data['selection'][`addon${i}`] == null || data['selection'][`quantity${i}`] ==null){
            setResp({status:403,message:"Please fill in all values.", contents: "'"});
            return;
        }
        store[data['selection'][`addon${i}`]] = data['selection'][`quantity${i}`];
    }   
    let allowedTypes = [];
    console.log(data);
    for (let dat of data['acceptedTypes']) {
        allowedTypes.push(dat.id);
    }
    finalDat["defaultSelection"] = store;
    finalDat["allowedAddonTypes"] = allowedTypes;
    finalDat["isAvailable"] = true;
    finalDat['shopId'] = shopId;
    finalDat["id"] = "N/A";
    console.log(finalDat);
    axios.post(`${domain}:${port}/CreateSingleton/${token}`,finalDat).then((resp)=>{
        setResp(resp);
    });
}

function increment(original, setter){
    var random = original.data;
    random.push(original.counter);
    setter({data:random, counter: original.counter+1});
}

function remove(original, setter, i){
    if(original.data.length > 1){
        let random = original.data
        var index = random.indexOf(i);
        random.splice(index, 1); 
        setter({data:random});  
    }
}


function generateOptions(data,form, setForm){
    //TODO: THIS MAY CAUSE ISSUES IN THE FUTURE, THE WAY THE DATA IS STORRED THIS NEEDS debugging.
    return(
    data.map((i,index)=>{
        return(
        <div class="input-group" key={index}>
            <select name="" onInput={e=>setForm(
                {...form,selection:{...form.selection,[`addon${index}`]:e.target.value}}
                )} id={`addon.${index}`} className="form-control rounded-0">
                <AddonsSelector/>
            </select>
            <input type="number" onInput={e=>setForm(
                {...form,selection:{...form.selection,[`quantity${index}`]:e.target.value}}
                )} class="form-control rounded-0" aria-label="Quantity" min="1" id={`quantity.${index}`}/>
        </div>
        );
    })
    );
}

function AddonsSelector(){
    const [data, setData] = useState(null);
    const shopId = GetShopId();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopAddons/${shopId}`).then((resp)=>{
            setData(resp.data);
        })
    },[]);

    if(data == null){
        return(
            <option value="null">Loading...</option>
        )
    }else{

        if(data.status == 200){
            return(data.contents.map((i)=>{
                const dat = JSON.parse(i);
                return(<option value={dat.id}>{dat.name}</option>);
            }));
        }
    }

}

function showError(result){
    if(result === null){
        return (
            <div className="errorContainer"></div>
        )
    }
    if(result.status !== 200){
        return (
            <div className="errorContainer text-danger"><small>{result.message}</small></div>
        )
    }
    return(
        <Redirect to="/vendor/menu/"/>
    )
}


export function CreateSingleton(){
    const [result, setResult] = useState(null);
    const [options, setOptions]  = useState({data:[1],counter:2});
    const [formData, setFormData] = useState({});
    const token = GetToken();
    const shopId = GetShopId();
    return(
        <div className="container my-3">
            <div className="display-5">Create Singleton</div>
                {showError(result)}
                <form onSubmit={(event) => {SingletonCreation(formData, options.data, event, setResult, token,shopId)}}>
                    <div className="form-group my-2">
                        <label for="name">Name:</label>
                        <input key="name" onInput={e=>setFormData({...formData,name:e.target.value})} type="text" className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group">
                        <label for="price">Price:</label>
                        <input key="priceInput" onInput={e=>setFormData({...formData,price:e.target.value})} type="number" className="form-control my-2" min="1" id="price"/>
                    </div>
                    <div className="form-group my-3">
                        <label for="location">Description:</label>
                        <textarea class="form-control" onInput={e=>setFormData({...formData,description:e.target.value})} aria-label="Description" id="description"></textarea>
                    </div>
                    <div className="form-group">
                        <label for="acceptedAddons">Accepted types of Addon(s):</label>
                        <select onInput={e=>setFormData({...formData,acceptedTypes:e.target.selectedOptions})} multiple class="form-control my-2" id="acceptedAddons">
                            <AddonTypes/>
                        </select>
                    </div>
                    <div className="my-3">
                        <label for="acceptedAddons">Default Addon Selections:</label>
                        <div className="row">
                            <div className="col text-muted">
                                <small>Addon</small>
                            </div>
                            <div className="col text-muted">
                                <small>Quantity</small>
                            </div>
                        </div>
                        <div className="my-2"></div>
                        {generateOptions(options.data, formData, setFormData)}
                        <div className="addbtn my-0">
                            <button type='button' onClick={()=>{increment(options, setOptions)}} className="btn btn-secondary w-100 rounded-0">Add Addon</button>
                            <button type="button" className="btn btn-danger w-100 rounded-0" onClick={()=>{remove(options, setOptions,1)}}>Remove One Addon</button>
                        </div>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>

                   
        </div>
);
}



function runCreateAddon(event, data, setResponse, token, shopId){
    event.preventDefault();
    let finalDat = {};
    finalDat['name'] = data.name;
    finalDat['price'] = data.price;
    let allowedTypes = [];
    console.log(data);
    for (let dat of data['types']) {
        allowedTypes.push(dat.id);
    }
    finalDat["addonTypes"] = allowedTypes;
    finalDat["isAvailable"] = true;
    finalDat['shopId'] = shopId;
    finalDat["id"] = "N/A";

    axios.post(`${domain}:${port}/CreateAddon/${token}`,finalDat).then((resp)=>{
        setResponse(resp);
    });

}


function AddonTypes(){
    const [data, setData] = useState(null);
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetAddonTypes/`).then((resp)=>{
            setData(resp)
        });
    },[]);

    if(data == null){
        return(<div class="spinner-grow" role="status">
            <span class="sr-only">Loading...</span>
        </div>);
    }else{
        if(data.data.status !== 200){
            return (
                <div>
                    Unable to load data.
                </div>
            )
        }
        let dat = JSON.parse(data.data.contents);
        return(dat.Addon_Types.map((i)=>{
            return(
                <option id={i.Label}>{i.Name}</option>
            )
        }));
    }
}


export function CreateAddon(){
    const [data, setData] = useState({});
    const [response, setResponse] = useState(null);
    const token = GetToken();
    const shopId = GetShopId();
    if(response!=null){
        if(response.status == 200){
            return(
                <Redirect to="/vendor/menu"/>
            )
        }
    }
    return(
            <div className="container my-3">
                <div className="display-5">Create Addon</div>
                <form onSubmit={(event) => runCreateAddon(event, data, setResponse,token, shopId)}>
                    <div className="form-group">
                        <label for="name">Name:</label>
                        <input key="name" onInput={e=>setData({...data,name:e.target.value})} type="text" className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group">
                        <label for="price">Price:</label>
                        <input key="priceInput" onInput={e=>setData({...data,price:e.target.value})} type="number" className="form-control my-2" min="0" id="price"/>
                    </div>
                    <div className="form-group">
                        <label for="price" >Type of Addon:</label>
                        <select multiple onInput={e=>setData({...data,types:e.target.selectedOptions})} class="form-control my-2" id="sel1">
                            <AddonTypes/>
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>
            </div>
    );
}


function runCreateFood(event, data, setResponse, token, shopId){
    event.preventDefault();
    let finalDat = {};
    finalDat['name'] = data.name;
    finalDat['price'] = data.price;
    finalDat['description'] = data.description;
    let components = [];
    for (let dat of data['components']) {
        components.push(dat.id);
    }
    finalDat["components"] = components;
    finalDat["isAvailable"] = true;
    finalDat['shopId'] = shopId;
    finalDat["id"] = "N/A";
    console.log(finalDat);
    axios.post(`${domain}:${port}/CreateFood/${token}`,finalDat).then((resp)=>{
        setResponse(resp);
    });
}

export function CreateFood(){
    const [data, setData] = useState({});
    const [response, setResponse] = useState(null);
    const token = GetToken();
    const shopId = GetShopId();
    if(response!=null){
        if(response.status == 200){
            return(
                <Redirect to="/vendor/menu"/>
            )
        }
    }
    return(
        <div className="container my-3">
                <div className="display-5">Create Food</div>
                <form onSubmit={(event)=>{runCreateFood(event,data,setResponse,token, shopId)}} >
                    <div className="form-group my-3">
                        <label for="name">Name:</label>
                        <input key="nameInput" type="text" onInput={e=>setData({...data,name:e.target.value})}  className="form-control my-2" id="name"/>
                    </div>
                    <div className="my-2"></div>
                    <div className="form-group my-3">
                        <label for="location">Description:</label>
                        <textarea class="form-control" onInput={e=>setData({...data,description:e.target.value})}  aria-label="Description" id="description"></textarea>
                    </div>
                    <div className="form-group my-3">
                        <label for="components">Components:</label>
                        <select multiple  onInput={e=>setData({...data,components:e.target.selectedOptions})} class="form-control my-2" id="components">
                            <SingletonOptions/>
                        </select>
                    </div>
                    <div className="form-group my-3">
                        <label for="price">Price:</label>
                        <div className="w-100"></div>
                        <small className="text-muted text-left">Enter -1 for autocalculation</small>
                        <input onInput={e=>setData({...data,price:e.target.value})} key="priceInput" type="number" className="form-control my-2" min="-1" id="price"/>
                    </div>
                    <button type="submit" className="btn btn-primary w-50">Submit </button>
                </form>
            </div>
);
}

function SingletonOptions(){
    const [data, setData] = useState(null);
    const shopId = GetShopId();
    useEffect(()=>{
        axios.get(`${domain}:${port}/GetShopSingletons/${shopId}`).then((resp)=>{
            setData(resp.data);
        })
    },[]);

    if(data == null){
        return(
            <option value="null">Loading...</option>
        )
    }else{

        if(data.status == 200){
            return(data.contents.map((i)=>{
                const dat = JSON.parse(i);
                return(<option id={dat.id}>{dat.name}</option>);
            }));
        }
    }
}