CREATE TABLE users (
                       user_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                       full_name VARCHAR(150) NOT NULL,
                       email VARCHAR(150) UNIQUE,
                       phone VARCHAR(20) NOT NULL UNIQUE,

                       password_hash TEXT NOT NULL,

                       profile_image TEXT,

                       gender VARCHAR(20)
                           CHECK (gender IN ('MALE', 'FEMALE', 'OTHER')),

                       date_of_birth DATE,

                       role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER'
                           CHECK (role IN ('CUSTOMER', 'DRIVER', 'ADMIN')),

                       status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'
                           CHECK (status IN ('ACTIVE', 'INACTIVE', 'BLOCKED', 'SUSPENDED')),

                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);


-- =========================================================
-- 2. ADMIN
-- =========================================================

CREATE TABLE admins (
                        admin_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

                        user_id UUID NOT NULL UNIQUE,

                        admin_code VARCHAR(50) UNIQUE,

                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                        CONSTRAINT fk_admin_user
                            FOREIGN KEY (user_id)
                                REFERENCES users(user_id)
                                ON DELETE CASCADE
);
