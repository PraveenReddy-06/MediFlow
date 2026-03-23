import { memo } from 'react';

const Navbar = () => {
  return (
    <div className="flex shrink-0 justify-between items-center p-5 bg-cyan-300">

      <div className="text-4xl">
        <h2>MediFlow.</h2>
      </div>  

      <div>
        <ul className="flex gap-5 items-center p-5">
            <li><a href="/home">Home</a></li>
            <li><a href="/about">About</a></li>
            <li><a href="/Specialties">Specialties</a></li>
            <div className="flex gap-5 bg-cyan-200 rounded-2xl px-2 ">
                <li><a href="/login">Login</a></li>
                <li><a href="/signup">SignUp</a></li>
            </div>
            
        </ul>
      </div>
    </div>
  );
};

export default memo(Navbar);