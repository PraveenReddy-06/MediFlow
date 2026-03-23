import { memo } from 'react';

const Card = () => {
  return (
    <div className="flex flex-col flex-shrink-0 w-48 rounded p-5 gap-5 bg-blue-400 items-center">
      <img className="w-40 h-40 rounded-full object-cover" src="https://plus.unsplash.com/premium_photo-1722873143746-232707ff0bb5?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" ></img>
      <h2>Dental</h2>
    </div>
  );
};

export default memo(Card);