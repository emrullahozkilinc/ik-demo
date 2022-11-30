import {createContext, useContext, useMemo} from "react";
import {useNavigate} from "react-router-dom";
import axios from "axios";
import {useSessionStorage} from "usehooks-ts";

const AuthContext = createContext(undefined);

export const AuthProvider = ({children}) => {
    const [user, setUser] = useSessionStorage("auth",{
        isLogin: false,
        token: ""
    });
    const navigate = useNavigate();

    //func to auth user
    const login = async (data) => {
        if (!user.isLogin) {
            try{
                await axios.post("http://localhost:8080/api/v1/auth/login",
                {
                        "username" : data.username,
                        "password" : data.password
                    }).then(res => {
                        if (res.status === 200) {
                            axios.defaults.headers.common['token'] = user.token;
                            setUser({
                                isLogin: true,
                                token: res.data.token
                            })
                            navigate("/employees")
                        } else {
                            console.log(res);
                        }
                    });
            }catch (error){
                console.log(error);
            }
        }
    }

    const logout = () => {
        setUser({
            isLogin: false,
            token: ""
        });
        navigate("/login");
    }
    
    const value = useMemo(() => ({user,login,logout}),[user])
    
    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => {
    return useContext(AuthContext);
}