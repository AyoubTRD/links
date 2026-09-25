import { createContext, useCallback, useState } from "react";

export type Credentials = { username: string; password: string };

export type AuthStateChange = { newState: string; token: string | null };
export type AuthStateChangeListener = (chage: AuthStateChange) => void;

export type AuthenticationResult =
    | {
        success: true;
    }
    | {
        error: true;
        errorMessage: string;
    };

export type AuthContextValue = {
    signup: (creds: Credentials) => Promise<AuthenticationResult>;
    login: (creds: Credentials) => Promise<AuthenticationResult>;
    logout: () => void;
    getToken: () => string;
};

export const AuthContext = createContext<AuthContextValue | null>(null);
