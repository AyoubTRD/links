import type { Route } from "./+types/login";

export function meta({ }: Route.MetaArgs): Route.MetaDescriptors {
    return [];
}

export default function Login() {
    return (
        <main className="flex flex-col items-center justify-center pt-16 pb-4">
            <h1 className="text-center text-6xl tracking-tighter font-black">
                Login
            </h1>

            <div className="max-w-[400px] w-full space-y-6 px-4 mt-16">
                <form className="flex flex-col gap-4 rounded-3xl border border-gray-200 p-6 dark:border-gray-700 ">
                    <div className="flex flex-col gap-2">
                        <label htmlFor="username">Username</label>
                        <input id="username" name="username" placeholder="admin" />
                    </div>

                    <div className="flex flex-col gap-2">
                        <label htmlFor="password">Password</label>
                        <input id="password" name="password" />
                    </div>

                    <button type="submit">Submit</button>
                </form>
            </div>

            <a href="/signup" className="underline mt-8">
                Signup
            </a>
        </main>
    );
}
