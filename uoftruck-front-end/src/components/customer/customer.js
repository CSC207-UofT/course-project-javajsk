import {useContext} from 'react'
import {UserContext} from '../../mechanisms/contexts';

function Customer(){
    const [userContext, setUserContext] = useContext(UserContext);
    if(userContext == null){
        return(
            <h1>
                {/* Convert this to redirect */}
                <button onClick={() => setUserContext("hello")}>
                    Login
                    </button> 
            </h1>
        );
    }
    return(
        <div className="display-2">
        <p className="text-align-left">
            {userContext}
        </p>
        {/* If user is logged in display the trucks n shit. */}
        <button onClick={()=> setUserContext(null)}>Logout</button>
        </div>
    );
}



export default Customer;