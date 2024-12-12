"use client";

import localFont from "next/font/local";
import "./globals.css";
import Link from "next/link";
import { usePathname } from "next/navigation";

const geistSans = localFont({
  src: "./fonts/GeistVF.woff",
  variable: "--font-geist-sans",
  weight: "100 900",
});
const geistMono = localFont({
  src: "./fonts/GeistMonoVF.woff",
  variable: "--font-geist-mono",
  weight: "100 900",
});

export default function RootLayout({ children }) {
  const pathname = usePathname();
  const showHeader = pathname !== "/";

  return (
    <html lang="en">
      <body className={`${geistSans.variable} ${geistMono.variable}`}>
        {showHeader && (
          <header className="menu-header">
            <nav>
              <Link href="/" style={{ margin: "5px" }}>
                Home
              </Link>
              <Link href="/users" style={{ margin: "5px" }}>
                Usuarios
              </Link>
              <Link href="/carrito" style={{ margin: "5px" }}>
                Carrito
              </Link>
              <Link href="/ventas" style={{ margin: "5px" }}>
                Ventas
              </Link>
            </nav>
          </header>
        )}
        <main>{children}</main>
      </body>
    </html>
  );
}
