import { useState } from "react";
import type { Route } from "./+types/signup";

export function meta({ }: Route.MetaArgs): Route.MetaDescriptors {
    return [
        {
            title: "Signup",
        },
    ];
}

export default function Signup() {
    const [creds, setCreds] = useState({
        username: "",
        password: "",
    });

    return (
        <main className="flex flex-col items-center justify-center pt-16 pb-4">
            <h1 className="text-center text-6xl tracking-tighter font-black">
                Sign up
            </h1>

            <div className="max-w-[400px] w-full space-y-6 px-4 mt-16">
                <form className="flex flex-col gap-4 rounded-3xl border border-gray-200 p-6 dark:border-gray-700 ">
                    <div className="flex flex-col gap-2">
                        <label htmlFor="username">Username</label>
                        <input
                            id="username"
                            name="username"
                            placeholder="admin"
                            value={creds.username}
                            onChange={(event) =>
                                setCreds((creds) => ({
                                    ...creds,
                                    username: event.target.value,
                                }))
                            }
                        />
                    </div>

                    <div className="flex flex-col gap-2">
                        <label htmlFor="password">Password</label>
                        <input
                            id="password"
                            name="password"
                            value={creds.password}
                            onChange={(event) =>
                                setCreds((creds) => ({
                                    ...creds,
                                    password: event.target.value,
                                }))
                            }
                        />
                    </div>

                    <button type="submit">Submit</button>
                </form>
            </div>

            <a href="/login" className="underline mt-8">
                Login
            </a>
        </main>
    );
}
