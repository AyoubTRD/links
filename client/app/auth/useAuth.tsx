import { useCallback, useState } from "react";
import {
    AuthContext,
    type AuthStateChangeListener,
    type Credentials,
} from "./AuthContext";

const AUTH_TOKEN_STORAGE_KEY = "auth_token";

export function AuthProvider({ children }: { children: React.ReactNode }) {
    // const apiClient = useApiClient();

    const [token, setToken] = useState<string | null>(() => {
        const storedToken = localStorage.getItem(AUTH_TOKEN_STORAGE_KEY);

        if (storedToken) return storedToken;
        return null;
    });

    const login = useCallback(async (creds: Credentials) => {
        //  const response = await apiClient.get("/users/login", {
        //      username: creds.username,
        //      password: creds.password,
        //  });
        //  if (response.ok) {
        //      const token = await response.json();
        //      // TODO: Return a success?
        //  } else {
        //      // TODO: Parse the error and return a proper error / state
        //  }
    }, []);
    const signup = useCallback((creds: Credentials) => { }, []);

    const getToken = useCallback(() => token, [token]);

    const logout = useCallback(() => {
        setToken(null);
        localStorage.removeItem(AUTH_TOKEN_STORAGE_KEY);
    }, [getToken]);

    const [stateChangeListeners, setStateChangeListeners] = useState<
        AuthStateChangeListener[]
    >([]);

    const onAuthStateChange = useCallback(
        (listener: AuthStateChangeListener) => {
            setStateChangeListeners([...stateChangeListeners, listener]);
        },
        [stateChangeListeners, setStateChangeListeners],
    );

    return (
        <AuthContext
            value={{
                login,
                logout,
                signup,
                getToken,
            }}
        >
            {children}
        </AuthContext>
    );
}
